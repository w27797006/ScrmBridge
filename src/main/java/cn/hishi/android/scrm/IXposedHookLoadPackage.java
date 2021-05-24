package cn.hishi.android.scrm;

import android.app.Application;

import cn.hishi.android.scrm.callbacks.XC_LoadPackage;
import cn.hishi.android.scrm.callbacks.XC_LoadPackage.LoadPackageParam;

/**
 * Get notified when an app ("Android package") is loaded.
 * This is especially useful to hook some app-specific methods.
 *
 * <p>This interface should be implemented by the module's main class. Xposed will take care of
 * registering it as a callback automatically.
 */
public interface IXposedHookLoadPackage extends IXposedMod {
	/**
	 * This method is called when an app is loaded. It's called very early, even before
	 * {@link Application#onCreate} is called.
	 * Modules can set up their app-specific hooks here.
	 *
	 * @param lpparam Information about the app.
	 * @throws Throwable Everything the callback throws is caught and logged.
	 */
	void handleLoadPackage(LoadPackageParam lpparam) throws Throwable;

	/** @hide */
	final class Wrapper extends XC_LoadPackage {
		private final IXposedHookLoadPackage instance;
		private final String apkPath;

		public Wrapper(IXposedHookLoadPackage instance, String apkPath) {
			this.instance = instance;
			this.apkPath = apkPath;
		}
		@Override
		public void handleLoadPackage(LoadPackageParam lpparam) throws Throwable {
			instance.handleLoadPackage(lpparam);
		}

		@Override
		public String getApkPath() {
			return apkPath;
		}
	}
}
