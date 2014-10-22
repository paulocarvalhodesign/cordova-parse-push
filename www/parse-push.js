var parsePushPlugin = {
	initialize: function(appId, clientKey, success, failure) {
		cordova.exec(
			success,
			failure,
			'ParsePushPlugin',
			'initialize',
			[appId, clientKey]
		);
	}
};

alert("loaded parsePushPlugin");

module.exports = parsePushPlugin;