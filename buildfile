require 'dependencies'
require 'version_numbers'

define 'RSSReader' do

		project.version = VERSION_NUMBER
		project.group = 'com.rssreader'
		
		puts 'Running buildr'

		define 'BusinessLogic' do
			project.version = BUSINESS_VERSION_NUMBER
			compile.with project('Persistence').package.to_s, project('Model').package.to_s, COMMON_COMPILE_LIBS
			test.with TESTING_LIBS, PERSIST_RUNTIME_LIBS
			package(:jar)
		end
		
		
		define 'Model' do
			project.version = MODEL_VERSION_NUMBER
			package(:jar)
		end		

		
		define 'Persistence' do
			project.version = PERSISTENCE_VERSION_NUMBER
			compile.with project('Model').package.to_s, COMMON_COMPILE_LIBS, PERSIST_COMPILE_LIBS, PERSIST_RUNTIME_LIBS
			test.with TESTING_LIBS,PERSIST_RUNTIME_LIBS
			package(:jar)
		end
		
		define 'Webservice' do
			project.version = WEB_VERSION_NUMBER
			compile.with project('BusinessLogic').package.to_s, project('Model').package.to_s, COMMON_COMPILE_LIBS, WS_COMPILE_LIBS, transitive(WS_COMPILE_TRANSITIVE_LIBS)
			test.with TESTING_LIBS
			package(:jar)
		end	
		
		define 'FeedDownload' do
			project.version = FEED_VERSION_NUMBER
			compile.with project('Persistence').package.to_s, project('Model').package.to_s, COMMON_COMPILE_LIBS, FEED_COMPILE_LIBS
			test.with TESTING_LIBS
			package(:jar)
		end

		pack = package(:war)
		
		pack.path('WEB-INF/')\
			.include path_to("#{project('Webservice').path_to}/WebContent/WEB-INF/*")
			
		pack.path('WEB-INF/lib')\
			.include(project('Persistence'))\
			.include(project('Model'))\
			.include(project('BusinessLogic'))\
			.include(project('Webservice'))\
			.include(project('FeedDownload'))\
			.include(artifacts(COMMON_COMPILE_LIBS))\
			.include(artifacts(PERSIST_COMPILE_LIBS))\
			.include(artifacts(PERSIST_RUNTIME_LIBS))\
			.include(artifacts(WS_COMPILE_LIBS))\
			.include(artifacts(transitive(WS_COMPILE_TRANSITIVE_LIBS)))\
			.include(artifacts(FEED_COMPILE_LIBS))
		
end





