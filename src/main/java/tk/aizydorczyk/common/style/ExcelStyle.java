package tk.aizydorczyk.common.style;

@FunctionalInterface
public interface ExcelStyle {
	ExcelStyleBuilder.Style createStyle(ExcelStyleBuilder styleBuilder);
}
