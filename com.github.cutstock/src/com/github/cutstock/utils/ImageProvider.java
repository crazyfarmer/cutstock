package com.github.cutstock.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.MultiKeyMap;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.github.cutstock.utils.images.IImage;

public class ImageProvider {

	private static MultiKeyMap cacheDescriptor = new MultiKeyMap();
	private static MultiKeyMap cacheImages = new MultiKeyMap();
	private static Map<ImageDescriptor, Image> cachedImages = new HashMap<ImageDescriptor, Image>();

	public static Image getImage(IImage image) {
		Image toReturn = (Image) cacheImages.get(image.getLocation(),
				image.getPath());
		if (toReturn == null || toReturn.isDisposed()) {
			ImageDescriptor desc = getImageDescriptor(image);
			toReturn = getImage(desc);
		}
		return toReturn;
	}

	private static Image getImage(ImageDescriptor desc) {
		Image toReturn = cachedImages.get(desc);
		if (toReturn == null || toReturn.isDisposed()) {
			toReturn = desc.createImage();
		}
		return toReturn;
	}

	public static ImageDescriptor getImageDescriptor(IImage image) {
		ImageDescriptor desc = (ImageDescriptor) cacheDescriptor.get(
				image.getLocation(), image.getPath());
		if (desc == null) {
			desc = ImageDescriptor.createFromFile(image.getLocation(),
					image.getPath());
			cacheDescriptor.put(image.getLocation(), image.getPath(), desc);
		}
		return desc;
	}
}
