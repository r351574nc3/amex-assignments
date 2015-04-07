/*
 * The MIT License (MIT)
 * 
 * Copyright (c) 2015 Leo Przybylski
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.github.r351574nc3.amex.assignment1.persistence;

import static com.github.r351574nc3.logging.FormattedLogger.*;

import java.io.File;

import redis.server.netty.RedisCommandDecoder;
import redis.server.netty.RedisCommandHandler;
import redis.server.netty.RedisReplyEncoder;
import redis.server.netty.SimpleRedisServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.DefaultEventExecutorGroup;


public class DefaultRedisRuntime implements RedisRuntime {
    public static final String REDIS_GROUP_CONTEXT_PROPERTY_NAME = DefaultRedisRuntime.class.getName()
        + File.pathSeparator + "redis";
        
    protected Thread proc;
    protected Integer port;

    public DefaultRedisRuntime() {
        setPort(6379);
    }

    public void setPort(final Integer port) {
        this.port = port;
    }
    public Integer getPort() {
        return this.port;
    }
    
        
    public RedisHandle start(final Boolean isForked) throws InterruptedException {
                    // Only execute the command handler in a single thread
        final RedisCommandHandler commandHandler = new RedisCommandHandler(new SimpleRedisServer());
        
        
        // Configure the server.
        final ServerBootstrap b = new ServerBootstrap();
        final DefaultEventExecutorGroup group = new DefaultEventExecutorGroup(1);
        
        try {
           b.group(new NioEventLoopGroup(), new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 100)
                .localAddress(getPort())
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            final ChannelPipeline p = ch.pipeline();
                            p.addLast(new RedisCommandDecoder());
                            p.addLast(new RedisReplyEncoder());
                            p.addLast(group, commandHandler);
                        }
                    });
            
            final StringBuffer message = new StringBuffer();
            
            // Start the server.
            if (isForked) {
                message.append("Forking Redis");
            }
            else {
                message.append("Starting Redis");
            }
            
            message.append("(port=").append(getPort()).append(") server...");
            info("%s", message.toString());
            
            final ChannelFuture f = b.bind();
            
            // Wait until the server socket is closed.
            if (!isForked) {
                f.sync();
                f.channel().closeFuture().sync();
            }
        }
        catch (Exception e) {
        }
           
        return new RedisHandle(group);
    }

    public void stop(final RedisHandle handle) throws InterruptedException {
        info("%s", "Shutting down Redis");
        handle.getGroup().shutdownGracefully();
    }
}
