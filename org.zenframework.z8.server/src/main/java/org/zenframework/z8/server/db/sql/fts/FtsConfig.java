package org.zenframework.z8.server.db.sql.fts;

import org.zenframework.z8.server.runtime.IObject;
import org.zenframework.z8.server.runtime.OBJECT;
import org.zenframework.z8.server.runtime.RCollection;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.server.types.integer;
import org.zenframework.z8.server.types.string;

public class FtsConfig extends OBJECT {

	public static class CLASS<T extends FtsConfig> extends OBJECT.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(FtsConfig.class);
		}

		@Override
		public Object newObject(IObject container) {
			return new FtsConfig(container);
		}
	}

	// (по умолчанию): длина документа не учитывается
	public static final integer NormDefault = new integer(0);
	// ранг документа делится на 1 + логарифм длины документа
	public static final integer NormLenLog = new integer(1);
	// ранг документа делится на его длину
	public static final integer NormLen = new integer(2);
	// ранг документа делится на среднее гармоническое расстояние между блоками (это реализовано только в ts_rank_cd)
	public static final integer NormHarmonic = new integer(4);
	// ранг документа делится на число уникальных слов в документе
	public static final integer NormUniqueWords = new integer(8);
	// ранг документа делится на 1 + логарифм числа уникальных слов в документе
	public static final integer NormUniqueWordsLog = new integer(16);
	// ранг делится своё же значение + 1
	public static final integer NormDivSelf = new integer(32);

	public static final FtsConfig.CLASS<FtsConfig> Default = new FtsConfig.CLASS<FtsConfig>(null);

	public FtsConfig(IObject container) {
		super(container);
	}

	public string config = null;
	public FtsQueryType queryType = FtsQueryType.Plain;
	@SuppressWarnings("rawtypes")
	public RCollection weight = null;
	public integer normalization = new integer(10);
	public bool coatingDensity = bool.False;

}
