package de.bht.fpa.strategypattern;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.bht.fpa.strategypattern.v1.Editor;
import de.bht.fpa.strategypattern.v1.EditorType;

public class EditorV1Test {
  private final String cSharpHelloWorld = "using System;namespace HelloWorld { class Hello { static void Main() { System.Console.WriteLine(\"Hello World!\"); } }";
  private final String cSharpHelloWorldFormatted = "using System;\nnamespace HelloWorld {\n class Hello {\n static void Main() {\n System.Console.WriteLine(\"Hello World!\");\n }\n }\n";
  private final String javaHelloWorld = "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello World!\"); } }";
  private final String javaHelloWorldFormatted = "public class HelloWorld {\n public static void main(String[] args) {\n System.out.println(\"Hello World!\");\n }\n }\n";

  @Test(expected = RuntimeException.class)
  public void shouldFailOnNullType() {
    Editor editor = new Editor();
    editor.setType(null);
    editor.format("dont care");
  }

  @Test
  public void shouldBreakOnOpenedCurlyBracesForCsharp() {
    Editor editor = new Editor();
    editor.setType(EditorType.CSHARP);
    assertThat(editor.format("{"), is("{\n"));
  }

  @Test
  public void shouldBreakOnClosedCurlyBracesForCsharp() {
    Editor editor = new Editor();
    editor.setType(EditorType.CSHARP);
    assertThat(editor.format("}"), is("}\n"));
  }

  @Test
  public void shouldBreakWithPrefixForCsharp() {
    Editor editor = new Editor();
    editor.setType(EditorType.CSHARP);
    assertThat(editor.format("{ölsaejkdsad"), is("{\nölsaejkdsad"));
  }

  @Test
  public void shouldBreakOnSemicolonForCsharp() {
    Editor editor = new Editor();
    editor.setType(EditorType.CSHARP);
    assertThat(editor.format(";ölsaejkdsad"), is(";\nölsaejkdsad"));
  }

  @Test
  public void shouldFormatHelloWorldForCsharp() {
    Editor editor = new Editor();
    editor.setType(EditorType.CSHARP);
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
    Editor editor = new Editor();
    editor.setType(EditorType.JAVA);
    String formatted = editor.format(javaHelloWorld);
    assertThat(formatted, is(javaHelloWorldFormatted));
  }

  @Test
  public void shouldBreakOnOpenedCurlyBracesForXML() {
    Editor editor = new Editor();
    editor.setType(EditorType.XML);
    assertThat(editor.format(">"), is(">\n"));
  }

}
