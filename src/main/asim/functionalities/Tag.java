package asim.functionalities;

public class Tag {

    int tagStartIndex;
    int tagEndIndex;
    String tagContent;

    public Tag() {
    }

    public Tag(int tagStartIndex, int tagEndIndex) {
        super();
        this.tagStartIndex = tagStartIndex;
        this.tagEndIndex = tagEndIndex;
    }

    public int getTagStartIndex()
    {
        return tagStartIndex;
    }

    public void setTagStartIndex(int tagStartIndex)
    {
        this.tagStartIndex = tagStartIndex;
    }

    public int getTagEndIndex()
    {
        return tagEndIndex;
    }

    public void setTagEndIndex(int tagEndIndex)
    {
        this.tagEndIndex = tagEndIndex;
    }

    public String getTagContent()
    {
        return tagContent;
    }

    public void setTagContent(String tagContent)
    {
        this.tagContent = tagContent;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "tagStartIndex=" + tagStartIndex +
                ", tagEndIndex=" + tagEndIndex +
                ", tagContent='" + tagContent + '\'' +
                '}';
    }

}
