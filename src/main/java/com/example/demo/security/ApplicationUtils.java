package com.example.demo.security;

import com.example.demo.model.ErrorModel;
import com.example.demo.model.ErrorResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

/**
 * Application Util class.
 *
 * @author quannl
 */
@Log4j2
public final class ApplicationUtils {

    private static final String NUMBERS = "0123456789";

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * Private constructor to prevent create new util class
     */
    private ApplicationUtils() {

    }

    /**
     * Convert {@link LocalDateTime} to format yyyyMMddHHmmssSSS
     *
     * @param dateTime {@link LocalDateTime}
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static String fromDatetimeSecondMillisecond(LocalDateTime dateTime) {
        if (null == dateTime) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_MILLISECOND_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Input: yyyy/MM/dd HH:mm
     * Output: yyyy/MM/dd(E)
     */
    public static String formatDateTimeDay(String input) {
        if (StringUtils.isEmpty(input)) {
            return "";
        }
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"))
                .format(DateTimeFormatter.ofPattern("yyyy/MM/dd(E)", Locale.JAPAN));
    }


    /**
     * Convert a string in format yyyyMMddHHmmssSSS to {@link LocalDateTime}
     *
     * @param input The input String to be converted
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static LocalDateTime toDatetimeSecondMillisecond(String input) {
        if (StringUtils.isEmpty(input)) return null;
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_MILLISECOND_FORMAT));
    }

    /**
     * Convert a string in format yyyyMMddHHmmssSSS to {@link LocalDateTime}
     *
     * @param input The input String to be converted
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static LocalDateTime toDateTimeMsFromLocalDateTime(String input) {
        if (StringUtils.isEmpty(input)) return null;
        LocalDateTime localDateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_FORMAT));
        String datetimeHourMinute = fromDatetimeSecond(localDateTime);
        datetimeHourMinute += String.format("%03d", localDateTime.getNano() / 1_000_000);
        return toDatetimeSecondMillisecond(datetimeHourMinute);
    }

    /**
     * Convert {@link LocalDateTime} to format yyyyMMddHHmmss
     *
     * @param dateTime {@link LocalDateTime}
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static String fromDatetimeSecond(LocalDateTime dateTime) {
        if (null == dateTime) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Convert {@link LocalDateTime} to format yyyyMMddHHmmss
     *
     * @param dateTime {@link LocalDateTime}
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static String fromTimeMilSecond(LocalDateTime dateTime) {
        if (null == dateTime) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.TIME_SECOND_MILLISECOND_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Convert a string in format yyyyMMddHHmmss to {@link LocalDateTime}
     *
     * @param input The input String to be converted
     * @return String with format yyyyMMddHHmmss
     */
    public LocalDateTime toDatetimeSecond(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_FORMAT));
    }


    /**
     * Extract LocalDate to Date in String format yyyyMMdd
     *
     * @param date {@link LocalDate}
     * @return String with format yyyyMMdd
     */
    public static String fromDate(LocalDate date) {
        if (null == date) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT);
        return date.format(formatter);
    }

    /**
     * Convert a string in format yyyyMMdd to {@link LocalDate}
     *
     * @param input The input String to be converted
     * @return String with format yyyyMMdd
     */
    public LocalDate toDate(String input) {
        if (StringUtils.isEmpty(input)) {
            return null;
        }
        return LocalDate.parse(input, DateTimeFormatter.ofPattern(ApplicationConstants.DATE_FORMAT));
    }

    public static String convertToLocalTimeStr(LocalDateTime localDateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_FORMAT);
        return localDateTime.format(outputFormatter);
    }

    /**
     * Convert a datetime-local format yyyy-MM-dd HH:mm to yyyyMMddHHmm
     *
     * @param dateTimeLocalStr
     * @return String with format yyyyMMddHHmm
     */
    public static String fromDateTimeLocal(String dateTimeLocalStr) {
        if (StringUtils.isBlank(dateTimeLocalStr)) {
            return "";
        }
        return LocalDateTime.parse(dateTimeLocalStr, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_FORMAT)).format(
                DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_MILLISECOND_FORMAT));
    }

    /**
     * Convert datetime-loal format yyyy-MM-dd HH:mm to yyyyMMddHHmm format plus 59_999 ms (search to condition)
     *
     * @param dateTimeLocalStr
     * @return
     */
    public static String fromDateTimeLocalPlusMilliseconds(String dateTimeLocalStr) {
        if (StringUtils.isBlank(dateTimeLocalStr)) {
            return "";
        }
        return LocalDateTime.parse(dateTimeLocalStr, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_FORMAT)).plus(59999, ChronoUnit.MILLIS).format(
                DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_SECOND_MILLISECOND_FORMAT));
    }

    /**
     * Convert a string in format yyyyMMddHHmmssSSS to {@link LocalDateTime}
     *
     * @param input The input String to be converted
     * @return String with format yyyyMMddHHmmssSSS
     */
    public static LocalDateTime toDatetimeMillisecond(String input) {
        if (StringUtils.isEmpty(input)) return null;
        return LocalDateTime.parse(input, DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_MILLISECOND_FORMAT));
    }

    /**
     * Create Fail response for failed bean validations
     *
     * @param errorModel {@link ErrorModel}
     * @return {@link ErrorResponse}
     */
    public static ErrorResponse createFailResponse(ErrorModel errorModel) {
        ErrorResponse response = new ErrorResponse();
        response.setErrorCode(errorModel.getErrorCode());
        response.setErrorMessage(errorModel.getErrorMessage());
        return response;
    }

    /**
     * Distinct by multiples properties.
     * https://howtodoinjava.com/java8/stream-distinct-by-multiple-fields/
     *
     * @param keyExtractors The key extractors
     * @param <T>           The entity type
     * @return {@link Predicate}
     */
    public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t -> {
            final List<?> keys = Arrays.stream(keyExtractors).map(ke -> ke.apply(t)).toList();
            return seen.putIfAbsent(keys, Boolean.TRUE) == null;
        };
    }

    /**
     * Distinct by a single property https://www.baeldung.com/java-streams-distinct-by
     *
     * @param keyExtractor The key extractor
     * @param <T>          The entity type
     * @return {@link Predicate}
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Generate random code with character from a-z and 0-9
     *
     * @param length the length of code
     * @return random code
     */
    public static String generateCode(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = secureRandom.nextInt(NUMBERS.length());
            char digit = NUMBERS.charAt(index);
            sb.append(digit);
        }
        return sb.toString();
    }

    /**
     * Generate random code with character from A-Z and 0-9
     * Format : XXXX-XXXX-XXXX-XXXX
     *
     * @return random code
     */
    public static String generateRandomCode() {
        StringBuilder sb = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < 19; i++) {
            if ((i + 1) % 5 == 0) {
                sb.append("-");
            } else {
                int index = secureRandom.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(index));
            }
        }

        return sb.toString();
    }

    /**
     * Generate sequence number
     *
     * @param sequenceNumber the value of sequence
     * @param length         the length of sequence output
     * @return generate sequence number
     */
    public static String generateSequenceNumber(long sequenceNumber, int length) {
        return String.format("%0" + length + "d", sequenceNumber);
    }

    /**
     * Generate password encode
     *
     * @param length {@link int}
     * @return {@link String}
     */
    public static String generatePassword(int length) {
        // Select at least one digit
        char randomDigit = ApplicationConstants.DIGITS.charAt(RANDOM.nextInt(ApplicationConstants.DIGITS.length()));
        // Select at least one letter
        char randomLetter = ApplicationConstants.LETTERS.charAt(RANDOM.nextInt(ApplicationConstants.LETTERS.length()));
        // Select at least one symbol
        char randomSpecialChar = ApplicationConstants.SYMBOLS.charAt(RANDOM.nextInt(ApplicationConstants.SYMBOLS.length()));

        // Choose the remaining characters
        String allChars = ApplicationConstants.DIGITS + ApplicationConstants.LETTERS + ApplicationConstants.SYMBOLS;
        StringBuilder remainingChars = new StringBuilder();
        for (int i = 3; i < length; i++) {
            remainingChars.append(allChars.charAt(RANDOM.nextInt(allChars.length())));
        }

        // Combine all the parts and shuffle them
        String result = randomDigit + "" + randomLetter + randomSpecialChar + remainingChars.toString();
        List<String> resultArray = Arrays.asList(result.split(""));
        Collections.shuffle(resultArray);
        result = String.join("", resultArray);

        return result;
    }

    /**
     * Format search like input (add escape character / before %, _ character to use in SQL LIKE search)
     *
     * @param input
     * @return formatted search input
     */
    public static String formatSearchLikeInput(String input) {
        if (StringUtils.isBlank(input)) {
            return input;
        }
        return input.replace("%", "/%").replace("_", "/_");
    }

    /**
     * Check escape character and convert to escape special character
     *
     * @param value the value convert
     * @return converted data
     */
    public static String escapeSpecialChars(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }

        String[] specialChars = {"/", "%", "_", "[", "]", "^", "-"};

        StringBuilder patternBuilder = new StringBuilder("[");
        for (String charToEscape : specialChars) {
            patternBuilder.append(Pattern.quote(charToEscape));
        }
        patternBuilder.append("]");

        Pattern pattern = Pattern.compile(patternBuilder.toString());
        Matcher matcher = pattern.matcher(value);

        StringBuilder escapedValue = new StringBuilder();
        while (matcher.find()) {
            matcher.appendReplacement(escapedValue, "/" + matcher.group());
        }
        matcher.appendTail(escapedValue);

        return escapedValue.toString();
    }

    /**
     * encode value
     *
     * @param value {@link String}
     * @return {@link String}
     */
    public static String encodeValue(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    /**
     * format {@link LocalDateTime} to format yyyy/MM/dd HH:mm
     *
     * @param dateTime {@link LocalDateTime}
     * @return String with format yyyy/MM/dd HH:mm
     */
    public static String formatDatetimeSecond(LocalDateTime dateTime) {
        if (null == dateTime) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ApplicationConstants.DATETIME_LOCAL_FORMAT);
        return dateTime.format(formatter);
    }

    /**
     * Format License
     *
     * @param code license format
     * @return String format XXXXXXXX-XXXXXXXX-XXXXXXXX-XXXXXXXX-XXXXXXXX-XXXXXXXX-XXXXXXXX-XXXXXXXX
     */
    public static String formatLicense(String code) {
        StringBuilder formattedCode = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            formattedCode.append(code.charAt(i));
            if ((i + 1) % 8 == 0 && i != code.length() - 1) {
                formattedCode.append("-");
            }
        }
        return formattedCode.toString();
    }
}
