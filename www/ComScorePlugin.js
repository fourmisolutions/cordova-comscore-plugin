

var ComScorePlugin = {
		
	// comscore sdk v5 - used in android
	initClient: function (customerID, customerKey, success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'initClient', [customerID, customerKey]);
	},
	notifyEnterForeground: function (success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'notifyEnterForeground', []);
	},
	notifyExitForeground: function (success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'notifyExitForeground', []);
	},
		
	// TODO: comscore sdk before v5 - retained for ios
	setCustomerData: function (customerID, customerKey, success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'setCustomerData', [customerID, customerKey]);
	},
	setAppName: function(appName, success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'setAppName', [appName]);
	},
	setAppContext: function(success, error){
		cordova.exec(success, error, 'ComScorePlugin', 'setAppContext', []);
	},
	onEnterForeground: function (success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'onEnterForeground', []);
	},
	onExitForeground: function (success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'onExitForeground', []);
	},
	autoUpdateForeground: function (interval, success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'autoUpdateForeground', [interval]);
	},
	autoUpdateBackground: function (interval, success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'autoUpdateBackground', [interval]);
	},
	start: function (success, error) {
		cordova.exec(success, error, 'ComScorePlugin', 'start', []);
	}
};

module.exports = ComScorePlugin;
