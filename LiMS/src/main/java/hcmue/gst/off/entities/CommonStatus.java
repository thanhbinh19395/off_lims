package hcmue.gst.off.entities;

/**
 * Created by dylan on 3/1/2017.
 */
public enum CommonStatus {
    PENDING(0),
    SOLVED(1);

    private final int value;

    CommonStatus(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
