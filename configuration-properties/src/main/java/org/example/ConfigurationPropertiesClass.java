package org.example;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "")
final class ConfigurationPropertiesClass {

	static final class My {

		static final class Setting {

			final int one;
			final String two;
			final boolean third;

			Setting(int one, String two, boolean third) {
				this.one = one;
				this.two = two;
				this.third = third;
			}

			@Override
			public String toString() {
				return "Setting [one=" + one + ", two=" + two + ", third="
						+ third + "]";
			}

		}

		static final class First {
			
			final Setting setting;

			public First(Setting setting) {
				this.setting = setting;
			}

			@Override
			public String toString() {
				return "First [setting=" + setting + "]";
			}						

		}

		static final class Second {
			final Setting setting;

			public Second(Setting setting) {
				this.setting = setting;
			}

			@Override
			public String toString() {
				return "Second [setting=" + setting + "]";
			}
			
		}

		final First first;
		final Second second;

		public My(First first, Second second) {
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString() {
			return "My [first=" + first + ", second=" + second + "]";
		}

	}

	final My my;

	ConfigurationPropertiesClass(My my) {
		this.my = my;
	}

	@Override
	public String toString() {
		return "ConfigurationPropertiesClass [my=" + my + "]";
	}
	

}
