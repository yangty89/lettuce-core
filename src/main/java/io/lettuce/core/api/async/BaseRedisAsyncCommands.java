/*
 * Copyright 2017-2024 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.lettuce.core.api.async;

import java.util.List;
import java.util.Map;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.output.CommandOutput;
import io.lettuce.core.protocol.CommandArgs;
import io.lettuce.core.protocol.ProtocolKeyword;

/**
 * Asynchronous executed commands for basic commands.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mark Paluch
 * @since 4.0
 * @generated by io.lettuce.apigenerator.CreateAsyncApi
 */
public interface BaseRedisAsyncCommands<K, V> {

    /**
     * Post a message to a channel.
     *
     * @param channel the channel type: key.
     * @param message the message type: value.
     * @return Long integer-reply the number of clients that received the message.
     */
    RedisFuture<Long> publish(K channel, V message);

    /**
     * Lists the currently *active channels*.
     *
     * @return List&lt;K&gt; array-reply a list of active channels, optionally matching the specified pattern.
     */
    RedisFuture<List<K>> pubsubChannels();

    /**
     * Lists the currently *active channels*.
     *
     * @param channel the key.
     * @return List&lt;K&gt; array-reply a list of active channels, optionally matching the specified pattern.
     */
    RedisFuture<List<K>> pubsubChannels(K channel);

    /**
     * Returns the number of subscribers (not counting clients subscribed to patterns) for the specified channels.
     *
     * @param channels channel keys.
     * @return array-reply a list of channels and number of subscribers for every channel.
     */
    RedisFuture<Map<K, Long>> pubsubNumsub(K... channels);

    /**
     * Returns the number of subscribers (not counting clients subscribed to patterns) for the specified shard channels.
     *
     * @param shardChannels channel keys.
     * @return array-reply a list of channels and number of subscribers for every channel.
     * @since 7.0
     */
    RedisFuture<Map<K, Long>> pubsubShardNumsub(K... shardChannels);

    /**
     * Returns the number of subscriptions to patterns.
     *
     * @return Long integer-reply the number of patterns all the clients are subscribed to.
     */
    RedisFuture<Long> pubsubNumpat();

    /**
     * Echo the given string.
     *
     * @param msg the message type: value.
     * @return V bulk-string-reply.
     */
    RedisFuture<V> echo(V msg);

    /**
     * Return the role of the instance in the context of replication.
     *
     * @return List&lt;Object&gt; array-reply where the first element is one of master, slave, sentinel and the additional
     *         elements are role-specific.
     */
    RedisFuture<List<Object>> role();

    /**
     * Ping the server.
     *
     * @return String simple-string-reply.
     */
    RedisFuture<String> ping();

    /**
     * Switch connection to Read-Only mode when connecting to a cluster.
     *
     * @return String simple-string-reply.
     */
    RedisFuture<String> readOnly();

    /**
     * Switch connection to Read-Write mode (default) when connecting to a cluster.
     *
     * @return String simple-string-reply.
     */
    RedisFuture<String> readWrite();

    /**
     * Instructs Redis to disconnect the connection. Note that if auto-reconnect is enabled then Lettuce will auto-reconnect if
     * the connection was disconnected. Use {@link io.lettuce.core.api.StatefulConnection#close} to close connections and
     * release resources.
     *
     * @return String simple-string-reply always OK.
     */
    RedisFuture<String> quit();

    /**
     * Wait for replication.
     *
     * @param replicas minimum number of replicas.
     * @param timeout timeout in milliseconds.
     * @return number of replicas.
     */
    RedisFuture<Long> waitForReplication(int replicas, long timeout);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param output the command output, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     */
    <T> RedisFuture<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output);

    /**
     * Dispatch a command to the Redis Server. Please note the command output type must fit to the command response.
     *
     * @param type the command, must not be {@code null}.
     * @param output the command output, must not be {@code null}.
     * @param args the command arguments, must not be {@code null}.
     * @param <T> response type.
     * @return the command response.
     */
    <T> RedisFuture<T> dispatch(ProtocolKeyword type, CommandOutput<K, V, T> output, CommandArgs<K, V> args);

    /**
     * @return {@code true} if the connection is open (connected and not closed).
     * @deprecated since 6.2. Use the corresponding {@link io.lettuce.core.api.StatefulConnection#isOpen()} method on the
     *             connection interface. To be removed with Lettuce 7.0.
     */
    @Deprecated
    boolean isOpen();

    /**
     * Reset the command state. Queued commands will be canceled and the internal state will be reset. This is useful when the
     * internal state machine gets out of sync with the connection.
     *
     * @deprecated since 6.2. Use the corresponding {@link io.lettuce.core.api.StatefulConnection#reset()} method on the
     *             connection interface. To be removed with Lettuce 7.0.
     */
    @Deprecated
    void reset();

    /**
     * Disable or enable auto-flush behavior. Default is {@code true}. If autoFlushCommands is disabled, multiple commands can
     * be issued without writing them actually to the transport. Commands are buffered until a {@link #flushCommands()} is
     * issued. After calling {@link #flushCommands()} commands are sent to the transport and executed by Redis.
     *
     * @param autoFlush state of autoFlush.
     * @deprecated since 6.2. Use the corresponding {@link io.lettuce.core.api.StatefulConnection#setAutoFlushCommands(boolean)}
     *             method on the connection interface. To be removed with Lettuce 7.0.
     */
    @Deprecated
    void setAutoFlushCommands(boolean autoFlush);

    /**
     * Flush pending commands. This commands forces a flush on the channel and can be used to buffer ("pipeline") commands to
     * achieve batching. No-op if channel is not connected.
     *
     * @deprecated since 6.2. Use the corresponding {@link io.lettuce.core.api.StatefulConnection#flushCommands()} method on the
     *             connection interface. To be removed with Lettuce 7.0.
     */
    @Deprecated
    void flushCommands();

}
