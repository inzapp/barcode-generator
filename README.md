# Barcode Generator
Simple barcode image generator using google zxing
<br><br>

## Download
https://github.com/inzapp/barcode-generator/releases
<br><br>

## Usage
```java
BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
String barcodeFilePath = barcodeGenerator.generate("barcode-content");
```
<br>

Set base path of image
```java
BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
barcodeGenerator.setBasePath("image/barcode/tmp");
String barcodeFilePath = barcodeGenerator.generate("barcode-content");
```
<br>

Set base path simply
```java
BarcodeGenerator barcodeGenerator = new BarcodeGenerator();
String barcodeFilePath = barcodeGenerator.generate("barcode-content", "image/barcode/tmp");
```
