package com.mattae.simal.modules.caffeine;

import com.foreach.across.core.AcrossModule;
import com.foreach.across.test.AbstractAcrossModuleConventionsTest;

/**
 * @author Arne Vandamme
 */
public class TestCaffeineModuleConventions extends AbstractAcrossModuleConventionsTest
{

	@Override
	protected AcrossModule createModule() {
		return new CaffeineModule();
	}
}
