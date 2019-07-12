function AboutUs($scope, $routeParams, ServletCall, $modal, $log) {
	
	
	
	$scope.CEO = [{
		name: 'NIRAV SHAH - President & CEO',
		info: 'Before launching Peterson Technology Partners Inc. in 1997, Nirav Shah had served as Managing Director of India Tribune Publications for 13 years.'
			+ 'India Tribune is a leading newspaper for Indians in the USA. Nirav was responsible for planning and executing strategies for successful growth of publication.'
			+ 'Nirav was also responsible for bringing new advertisers and identifying and evaluating new business opportunities to drive sustainable and profitable revenue growth.'
			+ 'Nirav was also responsible for creating a partnership between India Tribune and The Times of India, the fifth largest newspaper in the world.'
			+ 'While serving as Managing Director at India Tribune, Nirav also started companies that successfully grew into multi-million dollar businesses. '
			+ 'Nirav started Real Colors - an Import & Export business that specialized in bringing dyes & chemicals from India and selling it to textiles companies in the South; '
			+ 'Unique Insurance Agency which offered life & health insurance to clients nationwide; and Source America Inc. '
			+ 'which provided students an opportunity to receive a medical education within 6 years in Europe.'
			+ 'At Peterson Technology Partners, Nirav\'s main objective is to provide leadership in the areas of strategic planning, new business development and business expansion. '
			+ 'Nirav is also involved in design and development of information systems, business analysis, strategy development, monitoring and evaluating new technologies, overall project management and managing client relations.'
			+ 'Nirav has received a Bachelor\'s degree in Project Management and a Masters degree in Computer Information Systems from Northwestern University.'
	}, {
		name: 'DR. BALA V. BALACHANDRAN - Sr. Principal',
		info: 'J.L. Kellogg Professor of Accounting Information and Management Kellogg School of Management, Northwestern University.'
			+ 'Bala Balachandran began his teaching career in 1960 while a graduate student at Annamalai University, India.'
			+ 'In 1967 he moved to the University of Dayton and in 1971, to Carnegie-Mellon University, Pittsburgh, where he taught management courses while working on his doctorate. '
			+ 'In 1973 he joined the Kellogg School of Management faculty. He was Chairman of the Department of Accounting Information and Management from 1979 - 1983.'
			+ 'In 1984 he was appointed Professor of Accounting Information and Management and was Director of the Accounting Research Center from 1985-2006.'
			+ 'He is one of three Kellogg faculty members who started the Information Resource Management Program (IRM) at Northwestern in 1974.'
			+ 'He has authored more than 55 research articles and is currently writing a managerial accounting textbook with emphasis on cost management in an automated manufacturing environment.'
			+ 'He is department editor in accounting for Management Science, associate editor for The Accounting Review and on the editorial boards of Contemporary Accounting Research, and the Journal of Accounting, Auditing and Finance.'
			+ 'Professor Balachandran\'s research deals with performance evaluation, cost management, audit planning, allocation models, and forecasting.'
			+ 'His recent work includes auditors\' legal liability and game theoretic cost allocation models with transfer pricing.'
			+ 'His work has earned numerous scholastic honors, awards, and fellowships, and he serves as a consultant to senior management in industry, as well as to the U.S. Air Force, in the areas of accounting, forecasting, and strategic decision support systems.'
			+ 'He has provided executive education for various companies and the government and is the program director for "Managing Cost Information for Effective Strategic Decisions," a three-day program conducted at the James L. Allen Center each year during the spring and fall.'
	}, {
		name: 'BRIAN HERSHENHOUSE - Sr. Principal',
		info: 'Brian Hershenhouse began his sales and management career in 1982, working for Elek-Tek and later for AmeriData Distribution, presenting '
			+ 'and refining business solutions based on emerging technology. Managing a growing F100 clientele in Finance, Insurance, Real Estate and '
			+ 'Retail industries, he successfully sold millions of dollars annually in hardware and software, software development, implementation and support services.'
			+ 'Brian was further challenged in the development and leadership of a major account sales team supporting a multimillion dollar annual revenue '
			+ 'stream. In the late 1990\'s, having transitioned to a services-only consulting model, Brian assumed a Managing Director\'s role, responsible for '
			+ 'the development of the Metro Information Services-Chicago profit center, focused on project management & staffing in support of process '
			+ 'reengineering, software design and development and software quality assurance. Over 18 months, reversing a declining revenue stream, Brian '
			+ 'successfully tripled the Chicago operation\'s consulting staff, doubling the revenue stream, increasing measured customer satisfaction rates, '
			+ 'sales and recruiting effectiveness and consultant retention while increasing bottom line profitability by over 2%.'
			+ 'While focused on establishing a professional sales process and marketing, Brian is actively involved in creating a model organization dedicated '
			+ 'to providing customers with the highest quality of service and response, while enforcing mutually established quality implementation and support standards.'
	}];
	
	
	
	$scope.getInfo = function(data) {
		$scope.getCEO = [];
		switch (data){
		case 1: $scope.getCEO = $scope.CEO[0];break;
		case 2: $scope.getCEO = $scope.CEO[1]; break;
		case 3: $scope.getCEO = $scope.CEO[2]; break;
		}
		var modalInstance = $modal.open({
			templateUrl : 'showCEOInfo.html',
			controller : ShowCEO,
			resolve : {
				ceos : function() {
					return $scope.getCEO;
				}
			}
		});

		modalInstance.result.then(function(selectedItem) {
			$scope.selected = selectedItem;
		}, function() {
			$log.info('Modal dismissed at: ' + new Date());
		});
	};
	
};

var ShowCEO = function($scope, $modalInstance, ceos) {

	$scope.getCEO = ceos;
	$scope.name = $scope.getCEO.name;
	$scope.info = $scope.getCEO.info;
	

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};
};