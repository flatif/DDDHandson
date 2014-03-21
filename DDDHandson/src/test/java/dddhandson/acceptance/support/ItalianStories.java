package dddhandson.acceptance.support;

import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.ArrayUtils;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.model.ExamplesTableFactory;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.ConsoleOutput;
import org.jbehave.core.reporters.FilePrintStreamFactory.ResolveToSimpleName;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.MarkUnmatchedStepsAsPending;
import org.jbehave.core.steps.ParameterConverters;

public abstract class ItalianStories extends JUnitStories {
	
	Locale italianLocale = new Locale("it");
	
	@Override
	public Configuration configuration() {
		ClassLoader classLoader = this.getClass().getClassLoader();
		URL codeLocation = CodeLocations.codeLocationFromClass(this.getClass());
		Keywords keywords = new LocalizedKeywords(italianLocale);
		Configuration configuration = new MostUsefulConfiguration()
				.useKeywords(keywords)
				.useStepCollector(new MarkUnmatchedStepsAsPending(keywords))
				.useStoryParser(new RegexStoryParser(keywords))
				.useStoryLoader(new LoadFromClasspath(classLoader))
				.useDefaultStoryReporter(new ConsoleOutput(keywords))
				.useStoryReporterBuilder(
						new StoryReporterBuilder()
								.withCodeLocation(codeLocation)
								.withPathResolver(new ResolveToSimpleName())
								.withDefaultFormats()
								.withFormats(CONSOLE, HTML)
								.withFailureTrace(false).withKeywords(keywords))
				.useParameterConverters(
						new ParameterConverters()
								.addConverters(new ParameterConverters.ExamplesTableConverter(
										new ExamplesTableFactory(keywords))));
		return configuration;
	}
	
	@Override
	protected List<String> storyPaths() {
		String storyCodeLocation = CodeLocations.codeLocationFromClass(this.getClass()).getFile();
		String storyPatternItaliano = "**/" + nomeModulo() + "*.storia";
		System.out.println(storyPatternItaliano);
		return new StoryFinder().findPaths(storyCodeLocation, Arrays.asList(storyPatternItaliano), null);
	}
	
	@Override
	public InjectableStepsFactory stepsFactory() {
		//Questo metodo Factory qui sotto ha come argomento vararg di Object: dovrebbe quindi supportare più classi Steps.
		Object[] completeSteps = { step() };
		Object[] multipleSteps = steps();
		if (multipleSteps != null && multipleSteps.length > 0)
			ArrayUtils.addAll(completeSteps, multipleSteps);
		return new InstanceStepsFactory(configuration(), completeSteps);
	}
	
	public abstract String nomeModulo();
	
	public abstract Object step();
	
	public Object[] steps() {
		return null;
	}
	
}
