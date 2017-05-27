#import <Cordova/CDV.h>
#import <ComScore/ComScore.h>
#import "ComScorePlugin.h"

#if DEBUG == 0
#define DebugLog(...)
#elif DEBUG == 1
#define DebugLog(...) NSLog(__VA_ARGS__)
#endif

@implementation ComScorePlugin

- (void) pluginInitialize
{
    DebugLog(@"%s", __PRETTY_FUNCTION__);
}

- (void)initClient:(CDVInvokedUrlCommand*)command
{
    NSString *customerId = [command.arguments objectAtIndex:0];
    NSString *customerKey = [command.arguments objectAtIndex:1];
    
    DebugLog(@"%s customerId=%@, customerKey=%@", __PRETTY_FUNCTION__, customerId, customerKey);
    
    SCORPublisherConfiguration *myConfig =
        [SCORPublisherConfiguration publisherConfigurationWithBuilderBlock:^(SCORPublisherConfigurationBuilder *builder) {
                builder.publisherId = customerId;
                builder.publisherSecret = customerKey;
                builder.usagePropertiesAutoUpdateMode = SCORUsagePropertiesAutoUpdateModeForegroundOnly;
            }];
    [[SCORAnalytics configuration] addClientWithConfiguration:myConfig];
    
    DebugLog(@"%s start", __PRETTY_FUNCTION__);
    [SCORAnalytics start];
}

- (void) notifyEnterForeground:(CDVInvokedUrlCommand*)command
{
    DebugLog(@"%s", __PRETTY_FUNCTION__);
    [SCORAnalytics notifyEnterForeground];
}

- (void) notifyExitForeground:(CDVInvokedUrlCommand*)command
{
    DebugLog(@"%s", __PRETTY_FUNCTION__);
    [SCORAnalytics notifyExitForeground];
}

@end
