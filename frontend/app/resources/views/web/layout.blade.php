<!doctype html>
<html lang="{{ config('app.locale') }}">

<head>
	@include('web.layout.head')
</head>

<body class="flex flex-col h-screen">
	<header>
		@include('web.layout.header')
	</header>
	<main class="flex-grow">
		@yield('main')
	</main>
	<footer>
		@include('web.layout.footer')
	</footer>
</body>

</html>
