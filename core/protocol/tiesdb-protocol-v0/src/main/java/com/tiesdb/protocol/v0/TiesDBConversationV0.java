package com.tiesdb.protocol.v0;

import com.tiesdb.protocol.api.TiesDBProtocolPacketChannel;
import com.tiesdb.protocol.api.data.ElementReader;
import com.tiesdb.protocol.api.data.ElementWriter;
import com.tiesdb.protocol.api.data.Version;
import com.tiesdb.protocol.exception.TiesDBProtocolException;
import com.tiesdb.protocol.v0.api.TiesConversation;
import com.tiesdb.protocol.v0.element.TiesElement;
import com.tiesdb.protocol.v0.impl.ElementReaderImpl;
import com.tiesdb.protocol.v0.impl.ElementWriterImpl;

public class TiesDBConversationV0 implements TiesConversation {

	private final Version version;
	private final TiesDBProtocolPacketChannel packetChannel;
	private final TiesDBProtocolV0 protocol;

	private ElementReaderImpl reader;
	private ElementWriterImpl writer;

	public TiesDBConversationV0(TiesDBProtocolV0 protocol, TiesDBProtocolPacketChannel packetChannel, Version version) {
		this.protocol = protocol;
		this.packetChannel = packetChannel;
		this.version = version;
	}

	@Override
	public synchronized ElementReader<TiesElement> getReader() throws TiesDBProtocolException {
		return reader != null ? reader : (reader = getProtocol().createReader(packetChannel));
	}

	@Override
	public synchronized ElementWriter<TiesElement> getWriter() {
		return writer != null ? writer : (writer = getProtocol().createWriter(packetChannel));
	}

	@Override
	public Version getVersion() {
		return version;
	}

	@Override
	public TiesDBProtocolV0 getProtocol() {
		return protocol;
	}

}
