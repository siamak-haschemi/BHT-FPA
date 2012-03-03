package de.bht.fpa.strategypattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.bht.fpa.strategypattern.v2.CSharpEditor;
import de.bht.fpa.strategypattern.v2.Editor;
import de.bht.fpa.strategypattern.v2.JavaEditor;
import de.bht.fpa.strategypattern.v2.XMLEditor;

public class EditorV2Test {
  private final String cSharpHelloWorld = "using System;namespace HelloWorld { class Hello { static void Main() { System.Console.WriteLine(\"Hello World!\"); } }";
  private final String cSharpHelloWorldFormatted = "using System;\nnamespace HelloWorld {\n class Hello {\n static void Main() {\n System.Console.WriteLine(\"Hello World!\");\n }\n }\n";
  private final String javaHelloWorld = "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello World!\"); } }";
  private final String javaHelloWorldFormatted = "public class HelloWorld {\n public static void main(String[] args) {\n System.out.println(\"Hello World!\");\n }\n }\n";

  @Test
  public void shouldBreakOnOpenedCurlyBracesForCsharp() {
    Editor editor = new CSharpEditor();
    assertThat(editor.format("{"), is("{\n"));
  }

  @Test
  public void shouldBreakOnClosedCurlyBracesForCsharp() {
    Editor editor = new CSharpEditor();
    assertThat(editor.format("}"), is("}\n"));
  }

  @Test
  public void shouldBreakWithPrefixForCsharp() {
    Editor editor = new CSharpEditor();
    assertThat(editor.format("{ölsaejkdsad"), is("{\nölsaejkdsad"));
  }

  @Test
  public void shouldBreakOnSemicolonForCsharp() {
    Editor editor = new CSharpEditor();
    assertThat(editor.format(";ölsaejkdsad"), is(";\nölsaejkdsad"));
  }

  @Test
  public void shouldFormatHelloWorldForCsharp() {
    Editor editor = new CSharpEditor();
    String formatted = editor.format(cSharpHelloWorld);
    assertThat(formatted, is(cSharpHelloWorldFormatted));
    System.out.println("VORHER:");
    System.out.println(cSharpHelloWorld);
    System.out.println();
    System.out.println("NACHHER:");
    System.out.println(formatted);
  }

  @Test
  public void shouldFormatHelloWorldForJava() {
    Editor editor = new JavaEditor();
    String formatted = editor.format(javaHelloWorld);
    assertThat(formatted, is(javaHelloWorldFormatted));
  }

  @Test
  public void shouldBreakOnOpenedCurlyBracesForXML() {
    Editor editor = new XMLEditor();
    assertThat(editor.format(">"), is(">\n"));
  }

}
