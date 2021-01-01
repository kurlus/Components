package asim.functionalities;

import java.util.List;
import java.util.Vector;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagExtractor {

    private static final String sTagBracket = "<";
    private static final String seTagBracket = ">";
    private static final String scTagBracket = "</";

    public static void main(String[] args) throws Exception {

        String line = null;
        FileReader fileReader = new FileReader("D:\\temp\\tag-extractor.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        try {
            List<Tag> stTags = new Vector<>();
            List<Tag> edTags = new Vector<>();

            while ((line = bufferedReader.readLine()) != null) {
                if (TagExtractor.regexForHTMLXMlString(line)) {
                    TagExtractor.getStartTags(line, 0, stTags);
                    TagExtractor.getEndTags(line, 0, edTags);
                    System.out.println(line.substring(416, 550));
                } else {
                    System.out.println("None");
                }
            }
            for (Tag tg : stTags) {
                System.out.println(tg.toString());
            }
            System.out.println(" printing end indexes...");
            for (Tag tg : edTags) {
                System.out.println(tg.toString());
            }

        } finally {
            if (bufferedReader != null)
                bufferedReader.close();
        }

    }

    static void getStartTags(String line, int stIndex, List<Tag> stTags) {

        stIndex = line.indexOf("<", stIndex);
        if (stIndex < 0 || (stIndex + 1) > line.length()) return;

        boolean isClosing = line.substring(stIndex + 1).startsWith("/");
        int closingIndex = line.indexOf(">", stIndex);

        if (!isClosing && closingIndex > 0) {
            String tagContent = line.substring(stIndex + 1, closingIndex);

            if (tagContent != null && tagContent.length() > 0) {
                Tag tg = new Tag();
                tg.setTagStartIndex(stIndex);
                tg.setTagEndIndex(closingIndex);
                tg.setTagContent(tagContent);

                stTags.add(tg);
            }
        }
        if (closingIndex > 0 && (closingIndex + 1) <= line.length()) {
            int temp = closingIndex + 1;
            getStartTags(line, temp, stTags);
        }
    }

    static void getEndTags(String line, int stIndex, List<Tag> stTags) {

        stIndex = line.indexOf("</", stIndex);
        if (stIndex < 0 || stIndex > line.length()) return;
        int closingIndex = line.indexOf(">", stIndex);

        if (closingIndex > 0 && (stIndex + 2) <= line.length()) {
            String tagContent = line.substring(stIndex + 2, closingIndex);

            if (tagContent != null && tagContent.length() > 0) {
                Tag tg = new Tag();
                tg.setTagStartIndex(stIndex);
                tg.setTagEndIndex(closingIndex);
                tg.setTagContent(tagContent);

                stTags.add(tg);
            }
        }
        if (closingIndex > 0 && (closingIndex + 1) <= line.length()) {
            int temp = closingIndex + 1;
            getEndTags(line, temp, stTags);
        }
    }

    static void getTags(String line, Tag startTag, Tag endTag) {

        String tagContent = null;
        int sTagBracketIdx = -1, seTagBracketIdx = -1;

        if (startTag == null) {

            sTagBracketIdx = line.indexOf(sTagBracket);
            seTagBracketIdx = line.indexOf(seTagBracket);

            if (sTagBracketIdx >= 0 && seTagBracketIdx > 0)
                tagContent = line.substring(sTagBracketIdx + 1, seTagBracketIdx);
            else return;
        } else if (startTag != null) {

            if (startTag.getTagEndIndex() > 0 && startTag.getTagEndIndex() - 1 <= line.length()) {
                sTagBracketIdx = line.indexOf(sTagBracket, startTag.getTagEndIndex() - 1);
                seTagBracketIdx = line.indexOf(seTagBracket, sTagBracketIdx);
                tagContent = line.substring(sTagBracketIdx + 1, seTagBracketIdx);
            } else
                return;
        }

        startTag = new Tag();
        startTag.setTagStartIndex(sTagBracketIdx + 1);
        startTag.setTagEndIndex(seTagBracketIdx + 1);
        startTag.setTagContent(tagContent);

        int closingTag = line.indexOf(startTag.getTagContent(), startTag.getTagEndIndex());

        while (closingTag == -1) {
            sTagBracketIdx = line.indexOf(sTagBracket, startTag.getTagStartIndex());
            seTagBracketIdx = line.indexOf(seTagBracket, startTag.getTagEndIndex());

            if (sTagBracketIdx > 0 && seTagBracketIdx > 0 && sTagBracketIdx < line.length() && seTagBracketIdx < line.length())
                tagContent = line.substring(sTagBracketIdx + 1, seTagBracketIdx);
            else {
                startTag = null;
                break;
            }
            startTag.setTagStartIndex(sTagBracketIdx + 1);
            startTag.setTagEndIndex(seTagBracketIdx + 1);
            startTag.setTagContent(tagContent);

            closingTag = line.indexOf(startTag.getTagContent(), startTag.getTagEndIndex());
        }

        if (startTag == null)
            return;

        endTag = new Tag();
        String tempContent = null;
        int sIdx = line.lastIndexOf(scTagBracket.concat(startTag.getTagContent()), line.length());

        if (sIdx != -1) {
            String tagBracket = line.substring(sIdx, sIdx + 2);
            if (tagBracket.equals(scTagBracket)) {
                int eIdx = sIdx + startTag.getTagContent().length();
                String endTagContent = line.substring(sIdx + 2, eIdx + 2);
                String ceBracket = line.substring(eIdx + 2, eIdx + 3);
                String content = line.substring(startTag.getTagEndIndex(), sIdx);
                // System.out.println(content);
                if (contentHasTags(content))
                    getTags(content, null, null);
                else
                    System.out.println(content);

                if (seTagBracket.equals(ceBracket)) {
                    endTag.setTagStartIndex(sIdx + 1);
                    endTag.setTagEndIndex(eIdx + 4);
                    endTag.setTagContent(content);
                }
            }
        }
        if (startTag != null && endTag != null)
            getTags(line, endTag, null);

    }

    static boolean contentHasTags(String content) {
        if (content == null) return false;
        if (content.trim().contains(sTagBracket) || content.trim().contains(seTagBracket) || content.trim().contains(scTagBracket))
            return true;
        else
            return false;
    }

    static boolean regexForHTMLXMlString(String text) {

        String pattern = "<(.+)>([^<]+)</\\1>";

        Pattern patternregex = Pattern.compile(pattern);
        Matcher matcher = patternregex.matcher(text);
        return matcher.find();

    }

}
