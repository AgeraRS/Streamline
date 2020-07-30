package vo;

public class ImageWrapper {
    @Override
	public String toString() {
		return "ImageWrapper [token=" + token + ", imageSource=" + imageSource.toString();
	}

	public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ImageSource getImageSource() {
        return imageSource;
    }

    public void setImageSource(ImageSource imageSource) {
        this.imageSource = imageSource;
    }

    private String token;
    private ImageSource imageSource;
}
