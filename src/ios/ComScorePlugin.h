#import <Cordova/CDV.h>

@interface ComScorePlugin : CDVPlugin

- (void) initClient:(CDVInvokedUrlCommand*)command;
- (void) notifyEnterForeground:(CDVInvokedUrlCommand*)command;
- (void) notifyExitForeground:(CDVInvokedUrlCommand*)command;

@end
