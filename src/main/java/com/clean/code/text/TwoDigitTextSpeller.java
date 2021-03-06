package com.clean.code.text;

import static java.util.Arrays.asList;

import java.util.List;

public class TwoDigitTextSpeller extends SingleDigitTextSpeller {

	private static final String EMPTY_LITERAL = "";
	private final int INDEX_VALUE = 10;
	protected final String BLANK_SPACE_LITERAL = " ";

	// First 2 entries are left blank to facilitate easy fetch for numbers from 20.
	private final List<String> tensText = asList(EMPTY_LITERAL, EMPTY_LITERAL, "TWENTY", "THIRTY", "FORTY", "FIFTY", 
												 "SIXTY", "SEVENTY", "EIGHTY", "NINETY");
	
	private final List<String> tenTillTwentyText = asList("TEN", "ELEVEN", "TWELVE", "THIRTEEN", 
														  "FOURTEEN", "FIFTEEN", "SIXTEEN",
														  "SEVENTEEN", "EIGHTEEN", "NINETEEN");

	public String getText(int number) {
		return isBetween10and20(number) ? tenTillTwentyText.get(number - 10)
										: getTextForNumberGreaterThan20(number);
	}

	protected String getTextForNumberGreaterThan20(int number) {
		int remainingDigits = getRemainingDigits(number, INDEX_VALUE);
		int digitAtTenthDecimalIndex = getDigitAtHighestDecimalIndex(number, INDEX_VALUE);

		return remainingDigits == 0 ? tensText.get(digitAtTenthDecimalIndex) 
									: generateText(tensText.get(digitAtTenthDecimalIndex),
																super.getText(remainingDigits));
	}

	protected boolean isBetween10and20(int number) {
		return number >= 10 && number < 20;
	}

	protected int getDigitAtHighestDecimalIndex(int number,
			int decimalIndexValue) {
		return number / decimalIndexValue;
	}

	protected int getRemainingDigits(int number, int decimalIndexValue) {
		return number % decimalIndexValue;
	}

	protected String generateText(String... texts) {
		StringBuilder builder = new StringBuilder();
		for (String text : texts) {
			if (isNotBlank(text)) {
				builder.append(text);
				builder.append(BLANK_SPACE_LITERAL);
			}
		}
		return builder.toString().trim();
	}

	private boolean isNotBlank(String text) {
		return text.trim().length() > 0;
	}
}
