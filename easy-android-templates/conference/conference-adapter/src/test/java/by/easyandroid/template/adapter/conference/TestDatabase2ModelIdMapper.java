package by.easyandroid.template.adapter.conference;

import junit.framework.Assert;

import org.junit.Test;

public class TestDatabase2ModelIdMapper {

	private Database2ModelIdMapper mapper = new Database2ModelIdMapper();
	
	@Test
	public void testGetId() {
		Assert.assertEquals("1", mapper.getId("dbid1"));
		Assert.assertEquals("2", mapper.getId("dbid2"));
		Assert.assertEquals("1", mapper.getId("dbid1"));
	}
}