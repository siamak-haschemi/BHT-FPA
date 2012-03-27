package de.bht.fpa.strategypattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import de.bht.fpa.strategypattern.v3.AFormatter;
import de.bht.fpa.strategypattern.v3.BracesAndSemicolonFormatter;
import de.bht.fpa.strategypattern.v3.Editor;
import de.bht.fpa.strategypattern.v3.XMLFormatter;

public class EditorV3Test {
  private final String cSharpHelloWorld = "using System;namespace HelloWorld { class Hello { static void Main() { System.Console.WriteLine(\"Hello World!\"); } }";
  private final String cSharpHelloWorldFormatted = "using System;\nnamespace HelloWorld {\n class Hello {\n static void Main() {\n System.Console.WriteLine(\"Hello World!\");\n }\n }\n";
  private final String javaHelloWorld = "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello World!\"); } }";
  private final String javaHelloWorldFormatted = "public class HelloWorld {\n public static void main(String[] args) {\n System.out.println(\"Hello World!\");\n }\n }\n";
  private Editor bracesAndSemicolonEditor;
  private Editor xmlEditor;

  @Before
  public void setUp() {
    bracesAndSemicolonEditor = new Editor();
    AFormatter formatter = new BracesAndSemicolonFormatter();
    bracesAndSemicolonEditor.setFormatter(formatter);

    xmlEditor = new Editor();
    AFormatter xmlFormatter = new XMLFormatter();
    xmlEditor.setFormatter(xmlFormatter);
  }

  @Test
  public void shouldBreakOnOpenedCurlyBracesForCsharp() {
    assertThat(bracesAndSemicolonEditor.format("{"), is("{\n"));
  }

  @Test
  public void shouldBreakOnClosedCurlyBracesForCsharp() {
    assertThat(bracesAndSemicolonEditor.format("}"), is("}\n"));
  }

  @Test
  public void shouldBreakWithPrefixForCsharp() {
    assertThat(bracesAndSemicolonEditor.format("{ölsaejkdsad"), is("{\nölsaejkdsad"));
  }

  @Test
  public void shouldBreakOnSemicolonForCsharp() {
    assertThat(bracesAndSemicolonEditor.format(";ölsaejkdsad"), is(";\nölsaejkdsad"));
  }

  @Test
  public void shouldFormatHelloWorldForCsharp() {
    String formatted = bracesAndSemicolonEditor.format(cSharpHelloWorld);
    assertThat(formatted, is(cSharpHelloWorldFormatted));
    System.out.println("VORHER:");
    System.out.println(cSharpHelloWorld);
    System.out.println();
    System.out.println("NACHHER:");
    System.out.println(formatted);
  }

  @Test
  public void shouldFormatHelloWorldForJava() {
    String formatted = bracesAndSemicolonEditor.format(javaHelloWorld);
    assertThat(formatted, is(javaHelloWorldFormatted));
  }

  @Test
  public void shouldBreakOnOpenedCurlyBracesForXML() {
    assertThat(xmlEditor.format(">"), is(">\n"));
  }

}
