import {NextRequest, NextResponse} from 'next/server'

export function middleware(req: NextRequest) {
    const {pathname} = req.nextUrl;
    const cookies = req.cookies;

    if (pathname.startsWith('/_next') || pathname.startsWith('/static')) {
        return NextResponse.next();
    }

    if (pathname.startsWith('/api')) {
        return NextResponse.next();
    }

    const token = cookies.get('Authorization');

    if ((pathname === '/auth/login' || pathname === '/auth/register') && token) {
        const redirectUrl = req.nextUrl.clone();
        redirectUrl.pathname = '/';
        return NextResponse.redirect(redirectUrl);
    }

    if (pathname === '/auth/login' || pathname === '/auth/register') {
        return NextResponse.next();
    }


    if (!token) {
        const loginUrl = new URL('/auth/login', req.url);
        return NextResponse.redirect(loginUrl);
    }
    return NextResponse.next();
}