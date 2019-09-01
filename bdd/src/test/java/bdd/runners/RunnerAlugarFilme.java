package bdd.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/alugar_filme.feature", glue = "bdd.steps", tags = "~@ignore", plugin = "pretty", monochrome = true, snippets = SnippetType.CAMELCASE, strict = true)
public class RunnerAlugarFilme {

}
