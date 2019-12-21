package ua.edu.ucu.stream;

import ua.edu.ucu.function.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class AsIntStream implements IntStream {

    private static int[] input;

    private AsIntStream(int[] values) {
        input = values;
    }

    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() throws IllegalArgumentException {
        if (input.length == 0) throw new IllegalArgumentException();

        Double sum = 0.0;
        for (int i: input)
            sum += i;
        return sum/input.length;
    }

    @Override
    public Integer max() throws IllegalArgumentException {
        if (input.length == 0) throw new IllegalArgumentException();

        Integer Max = input[0];
        for (int i: input)
            if (i > Max)
                Max = i;

        return Max;
    }

    @Override
    public Integer min() throws IllegalArgumentException {
        if (input.length == 0) throw new IllegalArgumentException();

        Integer Min = input[0];
        for (int i: input)
            if (i < Min)
                Min = i;

        return Min;
    }

    @Override
    public long count() {
        return input.length;
    }

    @Override
    public Integer sum() throws IllegalArgumentException {
        if (input.length == 0) throw new IllegalArgumentException();

        Integer res = 0;
        for (int i: input)
            res += i;

        return res;
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        ArrayList<Integer> result = new ArrayList<>();
        for (int el: input)
            if (predicate.test(el))
                result.add(el);
        int[] result1 = new int[result.size()];
        for (int i = 0; i < result.size(); i++)
            result1[i] = result.get(i);
        return new AsIntStream(result1);
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int el: input)
            action.accept(el);
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        int[] result = new int[input.length];

        for (int i = 0; i < input.length; i++)
            result[i] = mapper.apply(input[i]);

        return new AsIntStream(result);
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        ArrayList<Integer> allEls = new ArrayList<>();
        for (int el: input)
            for (int elAfter: func.applyAsIntStream(el).toArray())
                allEls.add(elAfter);

        int[] result = new int[allEls.size()];
        for (int i = 0; i < allEls.size(); i++)
            result[i] = allEls.get(i);

        return new AsIntStream(result);
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        for (int el: input)
            identity = op.apply(identity, el);

        return identity;
    }

    @Override
    public int[] toArray() {
        return input;
    }

}
