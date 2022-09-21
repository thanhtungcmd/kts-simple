package com.tungbt.util.pdf

import com.lowagie.text.Font
import com.lowagie.text.pdf.BaseFont
import org.apache.poi.openxml4j.opc.OPCPackage
import org.apache.poi.xwpf.converter.pdf.PdfConverter
import org.apache.poi.xwpf.converter.pdf.PdfOptions
import org.apache.poi.xwpf.usermodel.*
import org.springframework.stereotype.Component
import java.awt.Color
import java.io.*

@Component
class PdfUtil {

    fun replaceText(inPath: String, outPath: String, dataMap: Map<String?, String?>) {
        try {
            val doc = XWPFDocument(OPCPackage.open(inPath))
            for (p in doc.paragraphs) {
                val runs = p.runs
                if (runs != null) {
                    for (r in runs) {
                        var text = r.getText(0)
                        for ((key, value) in dataMap) {
                            if (text != null && text.contains(key!!)) {
                                text = text.replace(key, value!!)
                                r.setText(text, 0)
                            }
                        }
                    }
                }
            }
            for (tbl in doc.tables) {
                for (row in tbl.rows) {
                    for (cell in row.tableCells) {
                        for (p in cell.paragraphs) {
                            for (r in p.runs) {
                                var text = r.getText(0)
                                for ((key, value) in dataMap) {
                                    if (text != null && text.contains(key!!)) {
                                        text = text.replace(key, value!!)
                                        r.setText(text, 0)
                                    }
                                }
                            }
                        }
                    }
                }
            }
            doc.write(FileOutputStream(outPath))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun convertToPDF(docPath: String, pdfPath: String) {
        try {
            val doc: InputStream = FileInputStream(docPath)
            val document = XWPFDocument(doc)
            val options = PdfOptions.create()
            options.fontProvider { _: String?, _: String?, size: Float, style: Int, color: Color? ->
                try {
                    val baseFont =
                        BaseFont.createFont("./template/font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED)
                    return@fontProvider Font(baseFont, size, style, color)
                } catch (e: Exception) {
                    throw IllegalArgumentException("Font was not found$e")
                }
            }
            val out: OutputStream = FileOutputStream(pdfPath)
            PdfConverter.getInstance().convert(document, out, options)
            println("Done")
        } catch (ex: FileNotFoundException) {
            println(ex.message)
        } catch (ex: IOException) {
            println(ex.message)
        }
    }

}