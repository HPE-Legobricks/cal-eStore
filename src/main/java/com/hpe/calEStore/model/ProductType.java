package com.hpe.calEStore.model;

public enum ProductType {

	HW("Hardware", "featured"), SW("Software", "software"), SE("Services",
			"latest");

	private final String name;
	private final String styleClass;

	private ProductType(String name, String styleClass) {
		this.name = name;
		this.styleClass = styleClass;
	}

	public String getName() {
		return this.name;
	}

	public String getStyleClass() {
		return this.styleClass;
	}

}
