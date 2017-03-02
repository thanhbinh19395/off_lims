package hcmue.gst.off.entities;

/**
 * Created by WIN8.1 on 08/02/2017.
 */
public enum BookTransactionStep {
    BORROWED(0),
    AVAILABLE(1),
    RESERVATED(2);
    private final Integer value;

    BookTransactionStep(final Integer newValue) {
        value = newValue;
    }

    public Integer getValue() { return value; }
}
