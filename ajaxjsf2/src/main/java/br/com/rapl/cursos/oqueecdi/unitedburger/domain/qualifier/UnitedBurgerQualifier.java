package br.com.rapl.cursos.oqueecdi.unitedburger.domain.qualifier;

import javax.inject.Qualifier;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;

import java.lang.annotation.Retention;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Target({ METHOD, FIELD, PARAMETER })
public @interface UnitedBurgerQualifier {

}
