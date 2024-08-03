package info.journeymap.shaded.kotlin.kotlin.ranges;

import info.journeymap.shaded.kotlin.kotlin.ExperimentalUnsignedTypes;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.SinceKotlin;
import info.journeymap.shaded.kotlin.kotlin.UInt;
import info.journeymap.shaded.kotlin.kotlin.UnsignedKt;
import info.journeymap.shaded.kotlin.kotlin.WasExperimental;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;

@Metadata(mv = { 1, 6, 0 }, k = 1, xi = 48, d1 = { "\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00172\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002:\u0001\u0017B\u0018\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003ø\u0001\u0000¢\u0006\u0002\u0010\u0006J\u001b\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0096\u0002ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0013\u0010\u000f\u001a\u00020\u000b2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u001a\u0010\u0005\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\u0004\u001a\u00020\u00038VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\t\u0010\bø\u0001\u0000\u0082\u0002\b\n\u0002\b\u0019\n\u0002\b!¨\u0006\u0018" }, d2 = { "Linfo/journeymap/shaded/kotlin/kotlin/ranges/UIntRange;", "Linfo/journeymap/shaded/kotlin/kotlin/ranges/UIntProgression;", "Linfo/journeymap/shaded/kotlin/kotlin/ranges/ClosedRange;", "Linfo/journeymap/shaded/kotlin/kotlin/UInt;", "start", "endInclusive", "(IILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getEndInclusive-pVg5ArA", "()I", "getStart-pVg5ArA", "contains", "", "value", "contains-WZ4Q5Ns", "(I)Z", "equals", "other", "", "hashCode", "", "isEmpty", "toString", "", "Companion", "info.journeymap.shaded.kotlin.kotlin-stdlib" })
@SinceKotlin(version = "1.5")
@WasExperimental(markerClass = { ExperimentalUnsignedTypes.class })
public final class UIntRange extends UIntProgression implements ClosedRange<UInt> {

    @NotNull
    public static final UIntRange.Companion Companion = new UIntRange.Companion(null);

    @NotNull
    private static final UIntRange EMPTY = new UIntRange(-1, 0, null);

    private UIntRange(int start, int endInclusive) {
        super(start, endInclusive, 1, null);
    }

    public int getStart_pVg5ArA() /* $VF was: getStart-pVg5ArA*/
    {
        return this.getFirst - pVg5ArA();
    }

    public int getEndInclusive_pVg5ArA() /* $VF was: getEndInclusive-pVg5ArA*/
    {
        return this.getLast - pVg5ArA();
    }

    public boolean contains_WZ4Q5Ns(/* $VF was: contains-WZ4Q5Ns*/
    int value) {
        int var2 = this.getFirst - pVg5ArA();
        if (UnsignedKt.uintCompare(var2, value) <= 0) {
            var2 = this.getLast - pVg5ArA();
            if (UnsignedKt.uintCompare(value, var2) <= 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return UnsignedKt.uintCompare(this.getFirst - pVg5ArA(), this.getLast - pVg5ArA()) > 0;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        return other instanceof UIntRange && (this.isEmpty() && ((UIntRange) other).isEmpty() || this.getFirst - pVg5ArA() == ((UIntRange) other).getFirst - pVg5ArA() && this.getLast - pVg5ArA() == ((UIntRange) other).getLast - pVg5ArA());
    }

    @Override
    public int hashCode() {
        return this.isEmpty() ? -1 : 31 * this.getFirst - pVg5ArA() + this.getLast - pVg5ArA();
    }

    @NotNull
    @Override
    public String toString() {
        return UInt.toString - impl(this.getFirst - pVg5ArA()) + ".." + UInt.toString - impl(this.getLast - pVg5ArA());
    }

    @Metadata(mv = { 1, 6, 0 }, k = 1, xi = 48, d1 = { "\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007" }, d2 = { "Linfo/journeymap/shaded/kotlin/kotlin/ranges/UIntRange$Companion;", "", "()V", "EMPTY", "Linfo/journeymap/shaded/kotlin/kotlin/ranges/UIntRange;", "getEMPTY", "()Lkotlin/ranges/UIntRange;", "info.journeymap.shaded.kotlin.kotlin-stdlib" })
    public static final class Companion {

        private Companion() {
        }

        @NotNull
        public final UIntRange getEMPTY() {
            return UIntRange.EMPTY;
        }
    }
}