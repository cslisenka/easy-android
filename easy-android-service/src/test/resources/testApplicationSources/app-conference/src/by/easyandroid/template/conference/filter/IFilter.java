package by.easyandroid.template.conference.filter;

public interface IFilter<T> {

	boolean isMatch(T object);
}