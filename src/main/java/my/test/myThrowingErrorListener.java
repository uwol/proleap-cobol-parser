package my.test;

import java.io.IOException;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.NotNull;

@SuppressWarnings("deprecation")
public class myThrowingErrorListener extends BaseErrorListener {

		@Override
		public void syntaxError(@NotNull final Recognizer<?, ?> recognizer,  final Object offendingSymbol,
				final int line, final int charPositionInLine, @NotNull final String msg,
				 final RecognitionException e) {
			throw new RuntimeException("syntax error in line " + line + ":" + charPositionInLine + " " + msg);
		}
	
}
