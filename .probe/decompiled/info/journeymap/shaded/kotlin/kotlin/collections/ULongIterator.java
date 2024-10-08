package info.journeymap.shaded.kotlin.kotlin.collections;

import info.journeymap.shaded.kotlin.kotlin.Deprecated;
import info.journeymap.shaded.kotlin.kotlin.DeprecationLevel;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.ULong;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.markers.KMappedMarker;
import java.util.Iterator;

/**
 * @deprecated
 */
@Deprecated(message = "This class is not going to be stabilized and is to be removed soon.", level = DeprecationLevel.ERROR)
@Metadata(mv = { 1, 6, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0007\b'\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u0002H\u0086\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0005\u0010\u0006J\u0015\u0010\u0007\u001a\u00020\u0002H&ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\b\u0010\u0006ø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\t" }, d2 = { "Linfo/journeymap/shaded/kotlin/kotlin/collections/ULongIterator;", "", "Linfo/journeymap/shaded/kotlin/kotlin/ULong;", "()V", "next", "next-s-VKNKU", "()J", "nextULong", "nextULong-s-VKNKU", "info.journeymap.shaded.kotlin.kotlin-stdlib" })
@SinceKotlin(version = "1.3")
public abstract class ULongIterator implements Iterator<ULong>, KMappedMarker {

    public final long next_s_VKNKU() /* $VF was: next-s-VKNKU*/
    {
        return this.nextULong - s - VKNKU();
    }

    public abstract long nextULong_s_VKNKU();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
}