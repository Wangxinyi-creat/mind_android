package com.pj.mind.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import com.pj.mind.utils.filter.CharFilter;


public class EditTextUtils {
    public static void setEditTextInhibitInputSpace(EditText editText) {
//        InputFilter filter = new InputFilter() {
//            @Override
//            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
//                return source.toString().trim().replaceAll("\\s*", "");
//            }
//        };
        editText.setFilters(new InputFilter[]{CharFilter.nrCharFilter()});
    }

    public static void setEditTextInhibitInputSpaceWithLength(EditText editText, int length) {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> source.toString().trim().replaceAll("\\s*", "");
        editText.setFilters(new InputFilter[]{filter, new InputFilter.LengthFilter(length)});
    }

    /**
     * 禁止EditText输入空格
     *
     * @param editText
     */
    public static void setEditTextInhibitInputSpacekongge(EditText editText) {
        InputFilter filter = (source, start, end, dest, dstart, dend) -> {
            if (source.toString().equals(" ")) {
                return "";
            } else {
                return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter});
    }

    public static void setEditTextInhibitInputSpaceTwo(EditText editText) {
        InputFilter inputFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence cs, int start,
                                       int end, Spanned spanned, int dStart, int dEnd) {
                if (cs.equals("")) { // for backspace
                    return cs;
                }
                if (cs.toString().matches("[a-zA-Z ]+")) {
                    return cs;
                }
                return "";
            }
        };
        editText.setFilters(new InputFilter[]{inputFilter});
    }
}
