package textcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextCalculator {
    private static final String BASIC_DELIMITER = ",|:";
    private static final String CUSTOM_DELIMITER = "//(.)\n(.*)";

    public List<String> tokenizer(String inputString) {
        String delimiter = getDelimiter(inputString);
        return Arrays.asList(inputString.replace("//" + delimiter + "\n", "").split(delimiter));
    }

    public String getDelimiter(String text) {
        Matcher matcher = Pattern.compile(CUSTOM_DELIMITER).matcher(text);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return BASIC_DELIMITER;
    }

    public List<Integer> toInt(List<String> tokens) {
        return tokens.stream().map((str) -> {
            if (Integer.parseInt(str) < 0) {
                throw new IllegalArgumentException();
            }
            return Integer.parseInt(str);
        }).collect(Collectors.toList());
    }

    public int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(number -> number).sum();
    }
}