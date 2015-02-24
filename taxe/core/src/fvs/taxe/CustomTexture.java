package fvs.taxe;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;

public class CustomTexture extends Texture {

	public CustomTexture(String internalPath) {
		super(internalPath);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(FileHandle file) {
		super(file);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(Pixmap pixmap) {
		super(pixmap);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(TextureData data) {
		super(data);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(FileHandle file, boolean useMipMaps) {
		super(file, useMipMaps);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(Pixmap pixmap, boolean useMipMaps) {
		super(pixmap, useMipMaps);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(FileHandle file, Format format, boolean useMipMaps) {
		super(file, format, useMipMaps);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(Pixmap pixmap, Format format, boolean useMipMaps) {
		super(pixmap, format, useMipMaps);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

	public CustomTexture(int width, int height, Format format) {
		super(width, height, format);
		this.setFilter(TextureFilter.Linear, TextureFilter.Linear);
	}

}
