/**
 * The type Counter.
 *
 * @author Shimon Rahamim
 */
public class Counter {
    private int count;

    /**
     * Increase.
     * the method increase the counter by the given number
     *
     * @param number the number
     */
// add number to current count.
    void increase(int number) {
        count += number;
    }

    /**
     * Decrease.
     * the method decrease the counter by the given number
     *
     * @param number the number
     */
// subtract number from current count.
    void decrease(int number) {
        count -= number;
    }

    /**
     * Gets value.
     * the method return the counter value
     *
     * @return the value
     */
// get current count.
    int getValue() {
        return count;
    }
}