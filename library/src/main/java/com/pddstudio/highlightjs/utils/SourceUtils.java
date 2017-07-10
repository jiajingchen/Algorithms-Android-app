package com.pddstudio.highlightjs.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * This Class was created by Patrick J
 * on 09.06.16. For more Details and Licensing
 * have a look at the README.md
 */

public class SourceUtils {

	public static String generateContent(String source, @NonNull String style, @Nullable String language, boolean supportZoom, boolean showLineNumbers) {
		return getStylePageHeader(supportZoom) +
				getSourceForStyle(style) +
				(showLineNumbers ? getLineNumberStyling() : "") +
				getScriptPageHeader(showLineNumbers) +
				getSourceForLanguage(source, language) +
				getTemplateFooter();
	}

	private static String getStylePageHeader(boolean enableZoom) {
		return "<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <meta charset=\"utf-8\">\n" +
				(enableZoom ? "" : "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, maximum-scale=1.0\">\n") +
				"    <style type=\"text/css\">\n" +
				"       html, body {\n" +
				"           width:100%;\n" +
				"           height: 100%;\n" +
				"           margin: 0px;\n" +
				"           padding: 0px;\n" +
				"       }\n" +
				"   </style>\n";
	}

	private static String getScriptPageHeader(boolean showLineNumbers) {
		return "    <script src=\"./highlight.pack.js\"></script>\n" +
				(showLineNumbers ? "<script src=\"./highlightjs-line-numbers.min.js\"></script>\n" : "") +
				"    <script>hljs.initHighlightingOnLoad();</script>\n" +
				(showLineNumbers ? "<script>hljs.initLineNumbersOnLoad();</script>\n" : "") +
				"</head>\n" +
				"<body style=\"margin: 0; padding: 0\">\n";
	}

	private static String getLineNumberStyling() {
		return "<style type=\"text/css\">\n" +
				".hljs-line-numbers {\n" +
				"\ttext-align: right;\n" +
				"\tborder-right: 1px solid #ccc;\n" +
				"\tcolor: #999;\n" +
				"\t-webkit-touch-callout: none;\n" +
				"\t-webkit-user-select: none;\n" +
				"\t-khtml-user-select: none;\n" +
				"\t-moz-user-select: none;\n" +
				"\t-ms-user-select: none;\n" +
				"\tuser-select: none;\n" +
				"}\n" +
				"</style>\n";
	}

	private static String getTemplateFooter() {
		return "</body>\n</html>\n";
	}

	private static String formatCode(String code) {
		return code.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}

	private static String getSourceForStyle(String style) {
		return String.format("<link rel=\"stylesheet\" href=\"./styles/%s.css\">\n", style);
	}

	private static String getSourceForLanguage(String source, String language) {
		if (language != null) { return String.format("<pre><code class=\"%s\">%s</code></pre>\n", language, formatCode(source)); } else {
			return String.format("<pre><code>%s</code></pre>\n", formatCode(source));
		}
	}

}
