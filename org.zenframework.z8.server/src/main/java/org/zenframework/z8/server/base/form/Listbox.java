package org.zenframework.z8.server.base.form;

import org.zenframework.z8.server.base.query.Query;
import org.zenframework.z8.server.base.table.value.Field;
import org.zenframework.z8.server.base.table.value.Link;
import org.zenframework.z8.server.json.Json;
import org.zenframework.z8.server.json.JsonWriter;
import org.zenframework.z8.server.runtime.IObject;
import org.zenframework.z8.server.runtime.RCollection;
import org.zenframework.z8.server.types.bool;
import org.zenframework.z8.server.types.integer;

public class Listbox extends Control {
	public static class CLASS<T extends Listbox> extends Control.CLASS<T> {
		public CLASS(IObject container) {
			super(container);
			setJavaClass(Listbox.class);
		}

		@Override
		public Object newObject(IObject container) {
			return new Listbox(container);
		}
	}

	public integer height;

	public Query.CLASS<? extends Query> query = null;
	public Link.CLASS<? extends Link> link = null;
	public RCollection<Field.CLASS<? extends Field>> sortFields = new RCollection<Field.CLASS<? extends Field>>();

	public Listbox(IObject container) {
		super(container);
		this.editable = bool.True;
	}

	@Override
	public void writeMeta(JsonWriter writer, Query query, Query context) {
		if(this.query == null)
			throw new RuntimeException("Listbox.query is null : displayName: '"  + displayName() + "'");

		if(link == null)
			throw new RuntimeException("Listbox.link is null : displayName: '"  + displayName() + "'");

		this.query.setContainer(null);
		query = this.query.get();

		super.writeMeta(writer, query, context);

		writer.writeProperty(Json.isListbox, true);
		writer.writeProperty(Json.header, displayName());
		writer.writeProperty(Json.icon, icon());

		writer.writeProperty(Json.height, height, new integer(4));

		writer.startObject(Json.query);
		writer.writeProperty(Json.id, query.classId());
		writer.writeProperty(Json.primaryKey, query.primaryKey().id());

		Field parentKey = query.parentKey();
		if(parentKey != null)
			writer.writeProperty(Json.parentKey, parentKey.id());

		writer.writeProperty(Json.totals, query.totals);
		writer.writeProperty(Json.text, query.displayName());
		writer.writeProperty(Json.link, link.id());
		writer.writeControls(Json.fields, query.getFormFields(), query, context);
		writer.writeControls(Json.columns, query.getColumns(), query, context);
		writer.writeSort(CLASS.asList(sortFields));
		writer.finishObject();
	}
}
