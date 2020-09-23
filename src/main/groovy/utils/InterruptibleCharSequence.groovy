package utils

class InterruptibleCharSequence implements CharSequence {
    CharSequence inner

    InterruptibleCharSequence(CharSequence inner) {
        super()
        this.inner = inner
    }

    char charAt(int index) {
        if (Thread.interrupted()) {
            throw new InterruptedException()
        }
        return inner.charAt(index)
    }

    int length() {
        return inner.length()
    }

    CharSequence subSequence(int start, int end) {
        return new InterruptibleCharSequence(inner.subSequence(start, end))
    }

    @Override
    String toString() {
        return inner.toString()
    }
}
