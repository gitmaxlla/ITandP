public class Pair <F, S> {
    private F first;
    private S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public void setFirst(F value) {
        first = value;
    }

    public void setSecond(S value) {
        second = value;
    }

    @Override
    public String toString() {
        return super.toString() + " -> (" + first.toString() + ", " + second.toString() + ")";
    }
}
