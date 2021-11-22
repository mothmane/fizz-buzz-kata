package ma.toto.rules;

public interface ReplacementRule {

  boolean appliable(Integer i);

  String getReplacement();
}
