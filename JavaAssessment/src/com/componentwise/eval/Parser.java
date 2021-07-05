package com.componentwise.eval;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


class Parser {

  private TagState state = TagState.CLOSE_TAG_ENDED;
  private String openTagName;
  private String content;
  private final Stack<String> tagStack = new Stack<>();

  void openAngle(int lineNumber, int position) {
    handleEvent(TagEvent.LESSTHAN, lineNumber, position);
  }

  void closedAngle(int lineNumber, int position) {
    handleEvent(TagEvent.GREATERTHAN, lineNumber, position);
  }

  void slash(int lineNumber, int position) {
    handleEvent(TagEvent.SLASH, lineNumber, position);
  }

  void text(String text, int lineNumber, int position) {
    this.content = text;
    handleEvent(TagEvent.TEXT, lineNumber, position);
  }

  private void handleEvent(TagEvent event, int lineNumber, int position) {

    switch (state) {

      case OPEN_TAG_STARTED:
        switch (event) {
          case LESSTHAN:
            break;
          case GREATERTHAN:
            this.state = TagState.OPEN_TAG_ENDED;
            break;
          case SLASH:
            this.state = TagState.CLOSE_TAG_STARTED;
            break;
          case TEXT:
            openTagName = content;
            this.state = TagState.OPEN_TAG_DECLARED;
            break;
        }
        break;

      case OPEN_TAG_DECLARED:
        switch (event) {
          case LESSTHAN:
            break;
          case GREATERTHAN:
            tagStack.push(openTagName);
            this.state = TagState.OPEN_TAG_ENDED;
            break;
          case SLASH:
            this.state = TagState.OPEN_TAG_AUTOCLOSE_STARTED;
            break;
          case TEXT:
            break;
        }
        break;

      case OPEN_TAG_AUTOCLOSE_STARTED:
        switch (event) {
          case LESSTHAN:
            break;
          case GREATERTHAN: 
            this.state = TagState.OPEN_TAG_ENDED;
            break;
          case SLASH:
            break;
          case TEXT:
            break;
        }
        break;

      case OPEN_TAG_ENDED:
        switch (event) {
          case LESSTHAN:
            this.state = TagState.OPEN_TAG_STARTED;
            break;
          case GREATERTHAN:
            break;
          case SLASH:
            break;
          case TEXT:
            break;
        }
        break;

      case CLOSE_TAG_STARTED:
        switch (event) {
          case LESSTHAN:
            break;
          case GREATERTHAN:
            this.state = TagState.CLOSE_TAG_ENDED;
            String currentElementName = tagStack.pop();
            if (!currentElementName.equals(openTagName)) {
              this.tagStack.push(currentElementName);
            }
          case SLASH: 
            break;
          case TEXT:
            openTagName = content;
            break;
        }
        break;

      case CLOSE_TAG_ENDED:
        switch (event) {
          case LESSTHAN:
            openTagName = "";
            state = TagState.OPEN_TAG_STARTED;
            break;
          case GREATERTHAN:
            break;
          case SLASH:
            break;
          case TEXT:
            break;
        }

        break;
    }
  }

  boolean allOpenAndCloseTagsMatch() {
    return tagStack.empty();
  }
}