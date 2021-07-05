package com.componentwise.eval;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TokenConverter {

  private static final Pattern spacesRegEx = Pattern.compile("^\\s+");
  private static Pattern wordRegEx = Pattern.compile("^\\w+");
  private Parser xmlParser;
  private int lineNum;
  private int colPos;

  TokenConverter(Parser parser) {
    this.xmlParser = parser;
  }

  void analyze(String s) {
    lineNum = 1;
    String lines[] = s.split("\n");
    for (String line : lines) {
    	System.out.println(line);
      analyzeLine(line);
      lineNum++;
    }
    if (!formComplete()) {
    	System.out.println("unmatched Tags in XML");
    }
  }

  private void analyzeLine(String line) {
      colPos = 0;
      while (colPos < line.length()) {
          readToken(line);
      }
  }

  private void readToken(String line) {
    if (!locateToken(line)) {
      colPos += 1;
    }
  }

  private boolean locateToken(String line) {
    return getWhiteSpace(line) || findSingleCharacterToken(line) || findName(line);
  }

  private boolean getWhiteSpace(String line) {
    Matcher matcher = spacesRegEx.matcher(line.substring(colPos));
    if (matcher.find()) {
      colPos += matcher.end();
      return true;
    }
    return false;
  }

  private boolean findSingleCharacterToken(String line) {
    String c = line.substring(colPos, colPos + 1);
    switch (c) {
      case "<":
        xmlParser.openAngle(lineNum, colPos);
        break;
      case ">":
        xmlParser.closedAngle(lineNum, colPos);
        break;
      case "/":
        xmlParser.slash(lineNum, colPos);
        break;
      default:
        return false;
    }
    colPos++;
    return true;
  }

  private boolean findName(String line) {
    Matcher nameMatcher = wordRegEx.matcher(line.substring(colPos));
    if (nameMatcher.find()) {
      xmlParser.text(nameMatcher.group(0), lineNum, colPos);
      colPos += nameMatcher.end();
      return true;
    }
    return false;
  }

  boolean formComplete() {
    return xmlParser.allOpenAndCloseTagsMatch();
  }

}
