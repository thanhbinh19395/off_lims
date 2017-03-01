package hcmue.gst.off.entities;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
public enum BookTransactionStep {
    BORROWED(0),
    AVAILABLE(1),
    RESERVATED(2);
    private final int value;

    BookTransactionStep(final int newValue) {
        value = newValue;
    }

    public int getValue() { return value; }
}
