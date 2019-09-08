package pro.buildmysoftware.translator;

import java.util.Objects;

public class Word {
	private String pl;
	private String en;

	public Word(String pl, String en) {
		this.pl = pl;
		this.en = en;
	}

	public String getPl() {
		return pl;
	}

	public String getEn() {
		return en;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Word word = (Word) o;
		return Objects.equals(pl, word.pl) && Objects
			.equals(en, word.en);
	}

	@Override
	public int hashCode() {
		return Objects.hash(pl, en);
	}
}
