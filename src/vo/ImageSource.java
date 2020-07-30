package vo;

public class ImageSource {

    private String thumbnailImage;
    private String fullSizeImage;
    private String resolution;
    private String category;
    private Integer fileSize;


    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getFullSizeImage() {
        return fullSizeImage;
    }

    public void setFullSizeImage(String fullSizeImage) {
        this.fullSizeImage = fullSizeImage;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "ImageWrapper{" +
                "thumbnailImage='" + thumbnailImage + '\'' +
                ", fullSizeImage='" + fullSizeImage + '\'' +
                ", resolution='" + resolution + '\'' +
                ", category='" + category + '\'' +
                ", fileSize=" + fileSize +
                ", sourceCreatedTime='" + sourceCreatedTime + '\'' +
                '}';
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public String getSourceCreatedTime() {
        return sourceCreatedTime;
    }

    public void setSourceCreatedTime(String sourceCreatedTime) {
        this.sourceCreatedTime = sourceCreatedTime;
    }

    private String sourceCreatedTime;
}
