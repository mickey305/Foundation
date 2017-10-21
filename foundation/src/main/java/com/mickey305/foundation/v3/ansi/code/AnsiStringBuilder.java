package com.mickey305.foundation.v3.ansi.code;

import com.mickey305.foundation.v3.validation.annotation.EscapeReject;

import java.io.IOException;
import java.io.Serializable;

public final class AnsiStringBuilder extends AbstractAnsiStringBuilder
        implements AnsiAppendable, CharSequence, Serializable {
    /** use serialVersionUID for interoperability */
    static final long serialVersionUID = 1L;

    private StringBuilder stringBuilder;
    private int codeLength;

    //===----------------------------------------------------------------------------------------------------------===//
    // Constructor                                                                                                    //
    //===----------------------------------------------------------------------------------------------------------===//
    public AnsiStringBuilder() {
        this(new StringBuilder());
    }

    public AnsiStringBuilder(int capacity) {
        this(new StringBuilder(capacity));
    }

    public AnsiStringBuilder(@EscapeReject String str) {
        this(new StringBuilder(str));
    }

    public AnsiStringBuilder(CharSequence seq) {
        this(new StringBuilder(seq));
    }

    public AnsiStringBuilder(@EscapeReject StringBuilder stringBuilder) {
        this.setStringBuilder(stringBuilder);
        this.setCodeLength(0);
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Methods                                                                                                        //
    //===----------------------------------------------------------------------------------------------------------===//
    @Override
    public AnsiStringBuilder append(CharSequence csq) throws IOException {
        // todo feature: validation-check impl
        this.getStringBuilder().append(csq);
        return this;
    }

    @Override
    public AnsiStringBuilder append(CharSequence csq, int start, int end) throws IOException {
        // todo feature: validation-check impl
        this.getStringBuilder().append(csq, start, end);
        return this;
    }

    @Override
    public AnsiStringBuilder append(char c) throws IOException {
        // todo feature: validation-check impl
        this.getStringBuilder().append(c);
        return this;
    }

    @Override
    public AnsiStringBuilder append(Escape escape) {
        this.getStringBuilder().append(escape.code());
        this.addCodeLength(escape.code().length());
        return this;
    }

    public AnsiStringBuilder append(@EscapeReject String target) {
        this.getStringBuilder().append(target);
        return this;
    }

    public AnsiStringBuilder append(@EscapeReject Object object) {
        this.getStringBuilder().append(object);
        return this;
    }

    public AnsiStringBuilder delete(@EscapeReject String target) {
        int index = 0;
        do {
            index = this.deleteFirst(target, index);
        } while (index >= 0);
        return this;
    }

    public AnsiStringBuilder delete(Escape escape) {
        int index = 0;
        do {
            index = this.deleteFirst(escape.code(), index);
            if (index >= 0)
                this.addCodeLength( - escape.code().length());
        } while (index >= 0);
        return this;
    }

    public AnsiStringBuilder deleteEscapeCode() {
        final Escape[] escapeLst = Escape.values();
        for (Escape escape: escapeLst) {
            this.delete(escape.code());
        }
        this.setCodeLength(0);
        return this;
    }

    public int deleteFirst(@EscapeReject String target) {
        return this.deleteFirst(target, 0);
    }

    public int deleteFirst( String target, int fromIndex) {
        StringBuilder sb = this.getStringBuilder();
        final int index = sb.indexOf(target, fromIndex);
        if (index >= 0) {
            this.delete(index, index + target.length());
            return index;
        }
        return -1;
    }

    public AnsiStringBuilder insert(int offset, @EscapeReject String str) {
        this.getStringBuilder().insert(offset, str);
        return this;
    }

    public AnsiStringBuilder insert(int offset, Escape escape) {
        this.insert(offset, escape.code());
        this.addCodeLength(escape.code().length());
        return this;
    }

    public AnsiStringBuilder insert(int offset, @EscapeReject Object object) {
        this.getStringBuilder().insert(offset, object);
        return this;
    }

    @Override
    public int length() {
        int escapeLen = (this.getCodeLength() == 0)
                ? 0
                : Escape.Reset.code().length();
        return this.getStringBuilder().length() + escapeLen;
    }

    public int length(Without without) {
        if (without == Without.EscapeCode) {
            return this.getStringBuilder().length() - this.getCodeLength();
        }
        return -1;
    }

    @Override
    public char charAt(int index) {
        return this.getStringBuilder().charAt(index);
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return this.getStringBuilder().subSequence(start, end);
    }

    @Override
    public String toString() {
        if (this.getCodeLength() != 0)
            this.append(Escape.Reset.code());
        StringBuilder sb = this.getStringBuilder();
        String target = sb.toString();
        if (this.getCodeLength() != 0) {
            int start = sb.lastIndexOf(Escape.Reset.code());
            int end = start + Escape.Reset.code().length();
            sb.delete(start, end);
        }
        return target;
//        String accent = (this.getCodeLength() == 0)
//                ? ""
//                : Escape.Reset.code();
//        return this.getStringBuilder().toString() + accent;

    }

    public String toString(Without without) {
        if (without == Without.EscapeCode) {
            String target = this.getStringBuilder().toString();
            target = target.replaceAll("\u001b\\[[;\\d]*m", "");
            return target;
        }
        return null;
    }

    private void addCodeLength(int offset) {
        this.setCodeLength(this.getCodeLength() + offset);
    }

    private AnsiStringBuilder delete(int start, int end) {
        this.getStringBuilder().delete(start, end);
        return this;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Accessor                                                                                                       //
    //===----------------------------------------------------------------------------------------------------------===//
    private StringBuilder getStringBuilder() {
        return stringBuilder;
    }

    private void setStringBuilder(StringBuilder stringBuilder) {
        this.stringBuilder = stringBuilder;
    }

    private int getCodeLength() {
        return codeLength;
    }

    private void setCodeLength(int codeLength) {
        this.codeLength = codeLength;
    }

    //===----------------------------------------------------------------------------------------------------------===//
    // Innerclass                                                                                                     //
    //===----------------------------------------------------------------------------------------------------------===//
    public enum Without {
        EscapeCode
    }
}
