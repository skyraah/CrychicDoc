package com.github.alexthe666.citadel.repack.jcodec.containers.mxf.model;

import com.github.alexthe666.citadel.repack.jcodec.common.logging.Logger;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class GenericDataEssenceDescriptor extends FileDescriptor {

    private UL dataEssenceCoding;

    public GenericDataEssenceDescriptor(UL ul) {
        super(ul);
    }

    @Override
    protected void read(Map<Integer, ByteBuffer> tags) {
        super.read(tags);
        Iterator<Entry<Integer, ByteBuffer>> it = tags.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Integer, ByteBuffer> entry = (Entry<Integer, ByteBuffer>) it.next();
            ByteBuffer _bb = (ByteBuffer) entry.getValue();
            switch(entry.getKey()) {
                case 15873:
                    this.dataEssenceCoding = UL.read(_bb);
                    it.remove();
                    break;
                default:
                    Logger.warn(String.format("Unknown tag [ FileDescriptor: " + this.ul + "]: %04x", entry.getKey()));
            }
        }
    }

    public UL getDataEssenceCoding() {
        return this.dataEssenceCoding;
    }
}